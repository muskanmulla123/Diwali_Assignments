import React, { useEffect, useState } from "react";
import "./Todos.css";

const Todos = () => {
  const [todos, setTodos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // fetch todo data on mount
    fetch("https://jsonplaceholder.typicode.com/todos")
      .then((response) => {
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        return response.json();
      })
      .then((data) => {
        setTodos(data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message || "Unknown error");
        setLoading(false);
      });
  }, []); // empty dependency array -> run once on mount

  return (
    <div className="todo-container">
      <h2>📋 To-Do List</h2>

      {loading && <p className="info">Loading data...</p>}

      {error && <p className="error">Server Error: {error}</p>}

      {!loading && !error && (
        <div className="todo-list">
          {todos.slice(0, 20).map((todo) => (
            <div
              key={todo.id}
              className={`todo-item ${todo.completed ? "completed" : ""}`}
            >
              <div className="todo-top">
                <span className="todo-id">#{todo.id}</span>
                <span className="todo-status">
                  {todo.completed ? "✅ Done" : "🕓 Pending"}
                </span>
              </div>
              <div className="todo-title">{todo.title}</div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Todos;
