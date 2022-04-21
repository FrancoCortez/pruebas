export interface State {
  isLoading: boolean;
  isLoader: boolean;
  isLogin?: {
    username: string,
    password: string,
  };
  error?: any
}


export const InitialState :State = {
  isLoader: false,
  isLoading: false,
}
