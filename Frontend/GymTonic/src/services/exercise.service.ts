import client from "@/client";
import {type AxiosInstance} from "axios";
import type {APIResponse, ExerciseList, ExerciseListPaginated, ExerciseListPaginatedResponse} from "@/types";


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

    async getAllPaginated(offset:number,size:number):APIResponse<ExerciseListPaginatedResponse> {
        try {
            const {data:result} = await this.client.get<ExerciseListPaginated>(`/exercise/pages?offset=${offset}&page=${size}`);
            return {
                result:{
                    data:result.data.content,
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

    async getByNamePaginated(name: string,offset:number,size:number):APIResponse<ExerciseListPaginatedResponse> {
        try {
            const {data:result} = await this.client.get<ExerciseListPaginated>(`/exercise/name=${name}/pages?offset=${offset}&page=${size}`);
            return {
                result:{
                    data: result.data.content,
                    status: result.status,
                }
            };
        } catch (error) {
            return {error:true};
        }
    }

    async getById(id: string):APIResponse<ExerciseList>{
        try {
            const {data:result} = await this.client.get<ExerciseList>(`/exercise/id=${id}`);            
            return {
                result:{
                    data: result.data,
                    status: result.status,
                }
            };
        } catch (error) {
            return {error:true};
        }
    }
}

export const exerciseService = new ExerciseService()