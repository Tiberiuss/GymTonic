import client from "@/client";
import {type AxiosInstance} from "axios";
import type {APIResponse} from "@/types";
import router from "@/router";


class UserService {
    client: AxiosInstance;

    constructor() {
        this.client = client;
    }

    isAuthenticated(){
        console.log(!!localStorage.getItem("token"))
        return !!localStorage.getItem("token");
    }

    removeToken(){
        localStorage.removeItem("token");
        router.go(0);
    }

    storeToken(token:string) {
        localStorage.setItem("token",token);
    }
    async register(user: string,password:string):APIResponse<any> {
        try {
            const {data:result} = await this.client.post(`auth/register`,{username:user,password});
            this.storeToken(result.token);
            return {result};
        } catch (error) {
            return {error:true};
        }
    }
    async login(user: string,password:string):APIResponse<any> {
        try {
            const {data:result} = await this.client.post(`auth/login`,{username:user,password});
            this.storeToken(result.token);
            return {result:{...result}};
        } catch (error) {
            return {error:true};
        }
    }
    async logout(name: string):APIResponse<any> {
        try {
            const {data:result} = await this.client.post(`auth/logout`);
            this.removeToken();
            return {...result};
        } catch (error) {
            return {error:true};
        }
    }
}

export const userService = new UserService()