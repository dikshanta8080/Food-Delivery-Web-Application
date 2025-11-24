import { Link } from "react-router-dom";

export default function LoginPage() {
  return (
    <div className="min-h-screen bg-gray-950 flex items-center justify-center px-4 relative overflow-hidden">
      {/* Subtle warm glows */}
      <div className="absolute inset-0">
        <div className="absolute top-20 left-20 w-96 h-96 bg-orange-600/10 rounded-full blur-3xl"></div>
        <div className="absolute bottom-20 right-20 w-80 h-80 bg-red-600/10 rounded-full blur-3xl"></div>
      </div>

      <div className="max-w-md w-full relative z-10">
        <div className="bg-gray-900/90 backdrop-blur-xl rounded-3xl shadow-2xl p-8 border border-gray-800">
          {/* Compact header — same as Signup */}
          <div className="text-center mb-8">
            <div className="w-16 h-16 mx-auto mb-4 bg-gradient-to-br from-orange-500 to-red-600 rounded-2xl flex items-center justify-center shadow-lg">
              <span className="text-2xl font-bold text-white">F</span>
            </div>
            <h2 className="text-3xl font-bold text-white">Welcome Back</h2>
            <p className="mt-2 text-gray-400">
              Sign in to continue your food journey
            </p>
          </div>

          <form className="space-y-5">
            <div>
              <label className="block text-sm font-medium text-gray-300 mb-2">
                Email Address
              </label>
              <input
                type="email"
                required
                placeholder="you@example.com"
                className="w-full px-5 py-3.5 bg-gray-800/70 border border-gray-700 rounded-xl text-white placeholder-gray-500 focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-300 mb-2">
                Password
              </label>
              <input
                type="password"
                required
                placeholder="••••••••"
                className="w-full px-5 py-3.5 bg-gray-800/70 border border-gray-700 rounded-xl text-white placeholder-gray-500 focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition"
              />
            </div>

            <div className="flex items-center justify-between text-sm">
              <label className="flex items-center text-gray-400">
                <input
                  type="checkbox"
                  className="mr-2 rounded text-orange-500"
                />
                <span>Remember me</span>
              </label>
              <a
                href="#"
                className="text-orange-400 hover:text-orange-300 font-medium"
              >
                Forgot password?
              </a>
            </div>

            <button
              type="submit"
              className="w-full bg-gradient-to-r from-orange-500 to-red-600 text-white py-3.5 rounded-xl font-bold text-lg shadow-xl hover:from-orange-600 hover:to-red-700 transition mt-6"
            >
              Log In
            </button>
          </form>

          <p className="mt-8 text-center text-gray-400">
            New to our platform?{" "}
            <Link
              to="/signup"
              className="font-bold text-orange-400 hover:text-orange-300 transition"
            >
              Create an account
            </Link>
          </p>
        </div>

        <p className="text-center mt-6 text-gray-500 text-sm">
          © 2025 FoodRush • Fresh Food, Fast Delivery
        </p>
      </div>
    </div>
  );
}
