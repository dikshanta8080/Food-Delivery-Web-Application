export interface PasswordContextType {
  email: string;
  otp: string;
  newPassword: string;
  setEmail: (email: string) => void;
  setOtp: (otp: string) => void;
  setNewPassword: (newPassword: string) => void;
}
