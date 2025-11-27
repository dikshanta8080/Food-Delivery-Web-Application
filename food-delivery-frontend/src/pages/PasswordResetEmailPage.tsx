import React, { useContext } from "react";
import { PasswordResetContext } from "../context/PasswordResetContext";
import { Link } from "react-router-dom";

const PasswordResetEmailPage: React.FC = () => {
  const passwordResetContext = useContext(PasswordResetContext);
  if (!passwordResetContext) return null;

  const { email, setEmail } = passwordResetContext;
  const handleFormSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log(email);
  };
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-6 text-center">
          Reset Your Password
        </h2>
        <form
          className="flex flex-col gap-4"
          onSubmit={(e) => {
            handleFormSubmit(e);
          }}
        >
          <input
            type="email"
            placeholder="yourexample@gmail.com"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
          />
          <button
            type="submit"
            className="bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition-colors"
          >
            Submit
          </button>
          <p>
            <Link to={"/otp"}> to to otp</Link>
          </p>
        </form>
      </div>
    </div>
  );
};

export default PasswordResetEmailPage;
