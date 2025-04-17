import './App.css'
import { AppProvider } from './provider'

export const App = () => {
    return (
        <AppProvider>
            <AppRouter />
        </AppProvider>
    )
}