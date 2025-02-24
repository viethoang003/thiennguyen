"use client";

import { useEffect, useState } from 'react';
import Layout from '../components/Layout';
import PostCard from '../components/PostCard';
import { fetchPosts } from '../utils/api';

export default function Home() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const loadPosts = async () => {
      try {
        const data = await fetchPosts();
        setPosts(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
    loadPosts();
  }, []);

  if (loading) return <Layout>Loading posts...</Layout>;
  if (error) return <Layout>Error: {error}</Layout>;

  return (
    <Layout>
      <h1 className="text-2xl font-bold mb-4">Recent Posts</h1>
      {posts.map((post) => (
        <PostCard key={post.id} post={post} />
      ))}
    </Layout>
  );
}