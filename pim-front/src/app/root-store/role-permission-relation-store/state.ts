export interface State {
    isLoadingData: boolean;
    isLoadingCreate: boolean;
    isLoadingDelete: boolean;
    isLoadingUpdate: boolean;
    isLoader: boolean;
    error?: any
}


export const InitialState: State = {
    isLoader: false,
    isLoadingData: false,
    isLoadingCreate: false,
    isLoadingDelete: false,
    isLoadingUpdate: false,
}
