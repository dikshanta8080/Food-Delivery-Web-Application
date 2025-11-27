import { createContext, useContext, useState } from "react";
import type { PasswordContextType } from "../types/PasswordResetContextType";

export const PasswordResetContext = createContext<PasswordContextType | null>(
  null
);
type props = { children: React.ReactNode };
export const PasswordResetProvider = ({ children }: props) => {
  const [email, setEmail] = useState<string>("");
  const [otp, setOtp] = useState<string>("");
  const [newPassword, setNewPassword] = useState<string>("");
  return (
    <PasswordResetContext.Provider
      value={{ email, otp, newPassword, setEmail, setOtp, setNewPassword }}
    >
      {children}
    </PasswordResetContext.Provider>
  );
};
