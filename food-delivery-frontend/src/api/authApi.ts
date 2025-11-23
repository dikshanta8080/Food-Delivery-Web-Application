import axios from "axios";
import {
  AuthApiResponse,
  LoginRequest,
  RegistrationRequest,
} from "../types/auth";

const BASE_URL = "http://localhost:8090/api/v1/auth";
export const signupApi = (data: RegistrationRequest) => {
  axios.post<AuthApiResponse>(`${BASE_URL}/register`, data);
};
