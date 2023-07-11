import axios from "axios";

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api/v1/',
    headers: {
        "Content-Type": "application/json"
    }
});

axiosInstance.interceptors.request.use((config) => {
    if (localStorage.getItem("token"))
        config.headers.Authorization = `Bearer ${localStorage.getItem("token")}`;
    return config;
})
export default axiosInstance;