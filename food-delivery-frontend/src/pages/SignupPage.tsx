import { Link } from "react-router-dom";
import { useState } from "react";
import type { RegistrationRequest } from "../types/AuthTypes";
import { signupApi } from "../services/AuthService.ts";
import type { AuthApiResponse } from "../types/AuthTypes";

export default function SignupPage() {
  const [registrationRequest, setRegistrationRequest] =
    useState<RegistrationRequest>({ name: "", email: "", password: "" });

  const handleFormSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(registrationRequest.name);
    console.log(registrationRequest.email);
    console.log(registrationRequest.password);
    const response: AuthApiResponse = await signupApi(registrationRequest);
    if (response.status == true) {
      alert("Signup successful");
    } else {
      alert("Signup failed " + response.message);
    }
  };
  return (
    <div className="min-h-screen bg-gray-950 flex items-center justify-center px-4 relative overflow-hidden">
      <div className="absolute inset-0">
        <div className="absolute top-20 left-20 w-96 h-96 bg-orange-600/10 rounded-full blur-3xl"></div>
        <div className="absolute bottom-20 right-20 w-80 h-80 bg-red-600/10 rounded-full blur-3xl"></div>
      </div>

      <div className="max-w-md w-full relative z-10">
        <div className="bg-gray-900/90 backdrop-blur-xl rounded-3xl shadow-2xl p-8 border border-gray-800">
          <div className="text-center mb-8">
            <div className="w-16 h-16 mx-auto mb-4 bg-gradient-to-br from-orange-500 to-red-600 rounded-2xl flex items-center justify-center shadow-lg">
              <span className="text-2xl font-bold text-white">F</span>
            </div>
            <h2 className="text-3xl font-bold text-white">Create Account</h2>
            <p className="mt-2 text-gray-400">Join us & enjoy fast delivery</p>
          </div>

          <form
            className="space-y-5"
            onSubmit={(e) => {
              handleFormSubmit(e);
            }}
          >
            <div>
              <label className="block text-sm font-medium text-gray-300 mb-2">
                Full Name
              </label>
              <input
                type="text"
                required
                value={registrationRequest.name}
                onChange={(e) => {
                  setRegistrationRequest({
                    ...registrationRequest,
                    name: e.target.value,
                  });
                }}
                placeholder="John Doe"
                className="w-full px-5 py-3.5 bg-gray-800/70 border border-gray-700 rounded-xl text-white placeholder-gray-500 focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-300 mb-2">
                Email Address
              </label>
              <input
                type="email"
                required
                value={registrationRequest.email}
                onChange={(e) => {
                  setRegistrationRequest({
                    ...registrationRequest,
                    email: e.target.value,
                  });
                }}
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
                value={registrationRequest.password}
                onChange={(e) => {
                  setRegistrationRequest({
                    ...registrationRequest,
                    password: e.target.value,
                  });
                }}
                required
                minLength={6}
                placeholder="••••••••"
                className="w-full px-5 py-3.5 bg-gray-800/70 border border-gray-700 rounded-xl text-white placeholder-gray-500 focus:ring-2 focus:ring-orange-500 focus:border-orange-500 transition"
              />
            </div>

            <button
              type="submit"
              className="w-full bg-gradient-to-r from-orange-500 to-red-600 text-white py-3.5 rounded-xl font-bold text-lg shadow-xl hover:from-orange-600 hover:to-red-700 transition mt-6"
            >
              Sign Up
            </button>
          </form>

          <p className="mt-8 text-center text-gray-400">
            Already have an account?{" "}
            <Link
              to="/login"
              className="font-bold text-orange-400 hover:text-orange-300 transition"
            >
              Log in
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
