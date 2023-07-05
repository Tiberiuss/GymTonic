import client from "@/client";
import {type AxiosInstance} from "axios";
import type {APIResponse} from "@/types";


class UserService {
    client: AxiosInstance;

    constructor() {
        this.client = client;
    }

    isAuthenticated(){
        return !!localStorage.getItem("user");
    }

    removeToken(){
        localStorage.removeItem("user");
    }

    storeToken(token:object) {
        localStorage.setItem("user",JSON.stringify(token));
    }
    async register(user: string,password:string):APIResponse<any> {
        try {
            //const {data:result} = await this.client.post(`/register`,{user,password});
            const result = {"user":1}
            this.storeToken(result);
            return {result};
        } catch (error) {
            return {error:true};
        }
    }
    async login(user: string,password:string):APIResponse<any> {
        try {
            //const {data:result} = await this.client.post(`/login`,{user,password});
            const result = {"user":1}
            this.storeToken(result);
            return {result:{...result}};
        } catch (error) {
            return {error:true};
        }
    }
    async logout(name: string):APIResponse<any> {
        try {
            const {data:result} = await this.client.post(`/logout`);
            this.removeToken();
            return {...result};
        } catch (error) {
            return {error:true};
        }
    }
}

export const userService = new UserService()