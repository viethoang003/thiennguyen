export default function PostCard({ post }) {
    return (
      <div className="bg-white p-6 rounded-lg shadow-md mb-4">
        <h3 className="text-xl font-bold">{post.title}</h3>
        <p className="text-gray-600">{post.description}</p>
        <div className="mt-4">
          <span className="text-sm text-gray-500">Status: {post.status}</span>
        </div>
      </div>
    );
  }