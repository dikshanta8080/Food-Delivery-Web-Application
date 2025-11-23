export interface AuthApiResponse {
  status: boolean;
  httpStatus: string;
  message: string;
  responseObject: {
    accessToken: string;
    refreshToken: string;
  };
}
export interface RegistrationRequest {
  name: "string";
  email: "string";
  password: "string";
}

export interface LoginRequest {
  email: string;
  password: string;
}
