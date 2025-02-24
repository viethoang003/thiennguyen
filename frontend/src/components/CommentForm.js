"use client"; 
import { useState } from 'react';

export default function CommentForm({ postId, onSubmit }) {
  const [content, setContent] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ postId, content });
    setContent('');
  };

  return (
    <form onSubmit={handleSubmit} className="mt-4">
      <textarea
        value={content}
        onChange={(e) => setContent(e.target.value)}
        placeholder="Add a comment..."
        className="w-full p-2 border rounded"
        required
      />
      <button type="submit" className="mt-2 bg-blue-600 text-white px-4 py-2 rounded">
        Submit
      </button>
    </form>
  );
}