import client from "@/client";
import type { APIResponse, Sets } from "@/types";
import {type AxiosInstance} from "axios";


class WorkoutService {
    client: AxiosInstance;

    constructor() {
        this.client = client;
    }

    async createSet(post: Sets): APIResponse<any> {
        try {
            const {data: result} = await this.client.post("/workout", post)
            return {
                result: {
                    status: result.status,
                }
            };
        } catch {
            return {error: true}
        }
    }

    async getAll(exercise_id:string):APIResponse<any> {
        try {
            const {data:result} = await this.client.get<any>(`/workout/exercise=${exercise_id}`);
            return {
                result:{
                    data: result.data,
                    status: result.status,
                }
            };
        } catch (e) {
            return {error:true};
        }
    }

}

export const workoutService = new WorkoutService()