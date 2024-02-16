import api from "./api"

export const getUsersRequest = () => api.get(`/users`);

export const getUserRequest = (id:any) => api.get(`/users/${id}`);

export const getCurrentUserRequest = () => api.get(`/users/current`);

export const updateCurrentUserRequest = (user:any) => api.put(`/users/current/update`,user);

export const deleteCurrentUserRequest = () => api.delete(`/users/current/delete`);
