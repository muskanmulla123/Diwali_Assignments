// server.js

require('dotenv').config(); // Load environment variables

const express = require('express');
const { body, validationResult } = require('express-validator');
const mysql = require('mysql2/promise');
const bcrypt = require('bcrypt');

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware to parse form data and JSON
app.use(express.urlencoded({ extended: true }));
app.use(express.json());

// Create MySQL pool
const pool = mysql.createPool({
  host: process.env.DB_HOST,
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_NAME,
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0
});

// ✅ Serve a registration form at /register (GET)
app.get('/register', (req, res) => {
  res.send(`
    <!DOCTYPE html>
    <html>
    <head>
      <title>Register User</title>
      <style>
        body { font-family: Arial; margin: 40px; }
        label { display: block; margin-top: 10px; }
        input { width: 250px; padding: 5px; margin-top: 5px; }
        .error { color: red; }
      </style>
    </head>
    <body>
      <h2>User Registration</h2>
      <form method="POST" action="/register">
        <label>User ID:
          <input type="text" name="user_id" required />
        </label>
        <label>First Name:
          <input type="text" name="first_name" required />
        </label>
        <label>Last Name:
          <input type="text" name="last_name" required />
        </label>
        <label>Email:
          <input type="email" name="email" required />
        </label>
        <label>Password:
          <input type="password" name="password" required />
        </label>
        <label>Confirm Password:
          <input type="password" name="confirm_password" required />
        </label>
        <button type="submit">Register</button>
      </form>
    </body>
    </html>
  `);
});

// ✅ Handle form submission (POST)
app.post(
  '/register',
  [
    body('user_id').trim().notEmpty().withMessage('User ID is required'),
    body('first_name').trim().notEmpty().withMessage('First name is required'),
    body('last_name').trim().notEmpty().withMessage('Last name is required'),
    body('email').isEmail().withMessage('Valid email is required'),
    body('password').isLength({ min: 6 }).withMessage('Password must be at least 6 characters'),
    body('confirm_password').custom((value, { req }) => {
      if (value !== req.body.password) throw new Error('Passwords must match');
      return true;
    })
  ],
  async (req, res) => {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
      // If validation fails, show errors on form
      return res.status(400).send(`
        <h3 style="color:red;">Error(s):</h3>
        <ul>${errors.array().map(err => `<li>${err.msg}</li>`).join('')}</ul>
        <a href="/register">Go Back</a>
      `);
    }

    const { user_id, first_name, last_name, email, password } = req.body;

    try {
      // Hash password
      const saltRounds = parseInt(process.env.BCRYPT_SALT_ROUNDS || '12', 10);
      const password_hash = await bcrypt.hash(password, saltRounds);

      // Insert into DB
      const sql = `
        INSERT INTO Users (user_id, first_name, last_name, email, password_hash)
        VALUES (?, ?, ?, ?, ?)
      `;
      await pool.execute(sql, [user_id, first_name, last_name, email, password_hash]);

      // Success message
      res.send(`
        <h2 style="color:green;">User registered successfully!</h2>
        <a href="/register">Register another user</a>
      `);
    } catch (err) {
      console.error('Database Error:', err);
      if (err.code === 'ER_DUP_ENTRY') {
        res.send(`<h3 style="color:red;">Email or User ID already exists!</h3>
                  <a href="/register">Go Back</a>`);
      } else {
        res.send(`<h3 style="color:red;">Server Error. Please try again later.</h3>`);
      }
    }
  }
);

// Root route
app.get('/', (req, res) => res.send('Server is running'));

// Start the server
app.listen(PORT, () => console.log(`✅ Server running on http://localhost:${PORT}`));
