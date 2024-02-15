import axios from 'axios'
import Cookies from "js-cookie";


const BASE_URL = 'http://localhost:7000/api';

const api = axios.create({
    baseURL:BASE_URL,
    withCredentials:true
});

api.interceptors.request.use(
    (config) => {
        const cookies = Cookies.get();
        const token = cookies.Authorization
        if (token && !(config.url?.startsWith('/auth'))) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default api;