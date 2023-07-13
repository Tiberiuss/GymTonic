import client from "@/client";
import type { APIResponse, Sets } from "@/types";
import {type AxiosInstance} from "axios";


class SetService {
    client: AxiosInstance;

    constructor() {
        this.client = client;
    }

    async createSet(post: Sets): APIResponse<any> {
        try {
            const {data:result} = await this.client.post("/set", post)
            return {
                result:{
                    status: result.status,
                }
            };
        }catch{
            return {error:true}
        }
    }

}

export const setService = new SetService()