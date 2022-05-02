export interface State {
  isLoading: boolean;
  isLoader: boolean;
  error?: any
}


export const InitialState: State = {
  isLoader: false,
  isLoading: false,
}
