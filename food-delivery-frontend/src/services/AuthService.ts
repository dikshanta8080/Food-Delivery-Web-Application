import axios, { type AxiosResponse } from "axios";
import type {
  AuthApiResponse,
  RegistrationRequest,
  LoginRequest,
} from "../types/AuthTypes";
const BASE_URL = "http://localhost:8090/api/v1/auth";

export const signupApi = async (data: RegistrationRequest) => {
  try {
    const response: AxiosResponse<AuthApiResponse> =
      await axios.post<AuthApiResponse>(`${BASE_URL}/register`, data);
    return response.data;
  } catch (error) {
    alert("error occured while signup");
    throw error;
  }
};

export const loginApi = async (data: LoginRequest) => {
  try {
    const response: AxiosResponse<AuthApiResponse> =
      await axios.post<AuthApiResponse>(`${BASE_URL}/login`, data);
    return response.data;
  } catch (error) {
    console.log(error);
    throw error;
  }
};
