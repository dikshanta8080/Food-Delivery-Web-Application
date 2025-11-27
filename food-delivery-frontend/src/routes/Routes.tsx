import { createBrowserRouter } from "react-router-dom";
import App from "../App";
import LoginPage from "../pages/LoginPage";
import SignupPage from "../pages/SignupPage";
import LandingPage from "../pages/LandingPage";
import PasswordResetEmailPage from "../pages/PasswordResetEmailPage";
import OtpPage from "../pages/OtpPage";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <App></App>,
    children: [
      { path: "login", element: <LoginPage /> },
      { path: "signup", element: <SignupPage /> },
      { path: "landing", element: <LandingPage /> },
      { path: "email", element: <PasswordResetEmailPage /> },
      { path: "otp", element: <OtpPage /> },
    ],
  },
]);
