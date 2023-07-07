export type APIResponse<T> = Promise<{result?: T, error?: boolean}>

export interface Exercise {
    id:string,
    material:string,
    muscle:string[],
    name:string,
    video:string[],
}

export interface ExerciseList {
    data: Exercise[],
    status: int
}

export interface Routine {
    name:string,
    user_id:number,
    date:string,
    exerciseMongo: Array<Exercise>
}