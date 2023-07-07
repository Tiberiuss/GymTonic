import client from "@/client";
import {type AxiosInstance} from "axios";
import type {APIResponse, Routine} from "@/types";

class RoutineService {
    client: AxiosInstance;

    constructor() {
        this.client = client;
    }

    async createRoutine(post: Routine): APIResponse<any> {
        try {
            const {data:result} = await this.client.post("/routine", post)
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

export const routineService = new RoutineService()