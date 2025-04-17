

import { QueryClientProvider } from '@tanstack/react-query';
import React from 'react'
import { ErrorBoundary } from 'react-error-boundary';
import { HelmetProvider } from 'react-helmet-async';

type AppProviderProps = {
    children: React.ReactNode;
}

export const AppProvider = ({ children }: AppProviderProps) => {
    return (
        <React.Suspense
            fallback={
                <div className='flex h-screen w-screen items-center justify-center'>
                    {/* <Spinner size="xl"/> */}
                </div>
            }
        >

            <ErrorBoundary>
                <HelmetProvider>
                    <QueryClientProvider>
                        <AuthLoader>
                            {children}
                        </AuthLoader>
                    </QueryClientProvider>
                </HelmetProvider>
            </ErrorBoundary>
        </React.Suspense>
    )
}

