import React, { useState } from 'react';
import axios from 'axios';

const UploadLine: React.FC = () => {
  const [line, setLine] = useState('');
  const [title, setTitle] = useState('');
  const [actor, setActor] = useState('');
  const [tags, setTags] = useState('');
  const [success, setSuccess] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      await axios.post('http://localhost:8080/myline/upload', {
        line,
        title,
        actor,
        tags: tags.split(',').map(tag => tag.trim())
      });
      setSuccess('Line uploaded successfully!');
      setError('');
      setLine('');
      setTitle('');
      setActor('');
      setTags('');
    } catch (err) {
      setError('Failed to upload line. Please try again.');
      setSuccess('');
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded shadow-md w-96 text-center">
        <h2 className="text-2xl font-semibold mb-4">Upload Famous Line</h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            value={line}
            onChange={(e) => setLine(e.target.value)}
            placeholder="Famous Line"
            className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
            required
          />
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            placeholder="Movie Title"
            className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
            required
          />
          <input
            type="text"
            value={actor}
            onChange={(e) => setActor(e.target.value)}
            placeholder="Actor"
            className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
            required
          />
          <input
            type="text"
            value={tags}
            onChange={(e) => setTags(e.target.value)}
            placeholder="Tags (comma separated)"
            className="w-full px-4 py-2 border rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          {error && <p className="text-red-500 text-sm">{error}</p>}
          {success && <p className="text-green-500 text-sm">{success}</p>}
          <button
            type="submit"
            className="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50"
          >
            Upload
          </button>
        </form>
      </div>
    </div>
  );
};

export default UploadLine;
