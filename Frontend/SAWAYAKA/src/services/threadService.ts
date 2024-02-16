import api from "./api";

export const createThreadRequest = (thread:any) => api.post(`/threads/new`,thread,)

export const getThreadsRequest = () => api.get(`/threads`)

export const getThreadRequest = (id:any) => api.get(`/threads/${id}`)

export const getCurrentThreadsRequest = () => api.get(`/threads/current`)

export const deleteThreadRequest = (id:any) => api.delete(`/threads/${id}/delete`)
