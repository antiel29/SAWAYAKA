import api from "./api";

export const getCommentsRequest = () => api.get(`/comments`)

export const getCommentRequest = (id:any) => api.get(`/comments/${id}`)

export const getCommentInThreadRequest = (id:any) => api.get(`/comments/thread/${id}`)

export const getCurrentCommentsRequest = () => api.get(`/comments/current`)

export const createCommentRequest = (comment:any) => api.post(`/comments/new`,comment)

export const deleteCommentRequest = (id:any) => api.delete(`/comments/${id}/delete`)
