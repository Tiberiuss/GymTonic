import axios from "axios";

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api/v1/',
    headers: {
        "Content-Type": "application/json"
    }
});

axiosInstance.interceptors.request.use((config) => {
    config.headers.Authorization = localStorage.getItem("user") ?? "";
    return config;
})
export default axiosInstance;