import client from "@/client";
import {type AxiosInstance} from "axios";
import type {APIResponse, ExerciseList} from "@/types";


class ExerciseService {
    client: AxiosInstance;

    constructor() {
        this.client = client;
    }

    async getAll():APIResponse<ExerciseList> {
        try {
            const {data:result} = await this.client.get<ExerciseList>("/exercise");
            return {
                result:{
                    data: result.data.splice(1,5),
                    status: result.status,
                }
            };
        } catch (e) {
            return {error:true};
        }
    }

    async getByName(name: string):APIResponse<ExerciseList> {
        try {
            const {data:result} = await this.client.get<ExerciseList>(`/exercise?name=${name}`);
            return {
                result:{
                    data: result.data.splice(1,5),
                    status: result.status,
                }
            };
        } catch (error) {
            return {error:true};
        }
    }
}

export const exerciseService = new ExerciseService()