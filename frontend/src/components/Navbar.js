import Link from 'next/link';

export default function Navbar() {
  return (
    <nav className="bg-blue-600 p-4 text-white">
      <div className="container mx-auto flex justify-between">
        <Link href="/">
          <span className="text-xl font-bold">Volunteer App</span>
        </Link>
        <div className="flex space-x-4">
          <Link href="/auth/login">Login</Link>
          <Link href="/auth/register">Register</Link>
          <Link href="/profile">Profile</Link>
        </div>
      </div>
    </nav>
  );
}