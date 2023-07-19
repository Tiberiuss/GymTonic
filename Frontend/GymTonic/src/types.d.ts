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

export interface ExerciseListPaginated {
    data: {
        content: Exercise[],
    },
    status: int
}

export interface ExerciseListPaginatedResponse {
    data: Exercise[],
    status: int
}

export interface Routine {
    name: string,
    date: string,
    exercise: Array<Exercise>
}

export interface selectedOption {
    date: string, 
    exercise: Array<Exercise>
    id: string,
    name: string,
}

export interface RoutineList {
    data: Routine[],
    status: int
}

/*export interface Sets {
    id: number,
    reps: number,
    weight: number
}

export interface Logs {
    exerciseId: string,
    sets: Array<Sets>
}

export interface payloadInitializeSets {
    routineId: string,
    exercises: Array<Exercise>
}

export interface payloadAddSets {
    index: number,
    set: Sets
}*/

export interface Sets {
    number: number,
    reps: number,
    weight: number,
    exerciseMongo: {
        id: number
    }
}

export interface payloadReps {
    number: number,
    reps: number
}

export interface payloadWeight {
    number: number,
    weight: number
}

export interface dayType{
    letter: string,
    day: number,
    today: boolean,
    iam: boolean
}