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

export interface Sets {
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
}

export interface payloadReps {
    index: number,
    setId: number,
    reps: number
}

export interface payloadWeight {
    index: number,
    setId: number,
    weight: number
}

export interface payloadDeleteSets {
    index: number,
    setId: number
}

export interface dayType{
    letter: string,
    day: number,
    today: boolean,
    iam: boolean
}