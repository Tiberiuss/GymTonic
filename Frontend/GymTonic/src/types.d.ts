export type APIResponse<T> = Promise<{result?: T, error?: boolean}>

export interface Exercise {
    id:string,
    material:string,
    muscle:string[],
    name:string,
    video:string[],
    steps:string[]
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

export interface WorkoutList {
    data: Sets[],
    status: int
}

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

export interface payloadCreateWorkout {
    routine: string,
    date: string
}

export interface dayType{
    letter: string,
    strDate: string,
    day: number,
    today: boolean,
    selected: boolean,
    workout: Sets
}