export interface AuthApiResponse {
  status: boolean;
  httpStatus: string;
  message: string;
  responseObject: {
    accessToken: string;
    refreshToken: string;
    user: {
      id: number;
      name: string;
      email: string;
      role: string;
      profileImageUrl: string;
    };
  };
}
export interface RegistrationRequest {
  name: string;
  email: string;
  password: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}
export interface User {
  id: number;
  name: string;
  email: string;
  role: string;
  profileImageUrl: string;
}
