import api from "./api";

export const loginRequest = (user:any) => api.post(`/auth/login`,user)

export const registerRequest = (user: any) => api.post(`/auth/register`,user)

export const verifyRequest = (token:any) => api.get(`/auth/verify?token=${token}`)

export const logoutRequest = (token:any) => api.post(`/auth/logout?token=${token}`)

