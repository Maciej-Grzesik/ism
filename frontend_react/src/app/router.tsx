import { paths } from '@/config/paths';
import { QueryClient, useQueryClient } from '@tanstack/react-query';
import { useMemo } from 'react';
import { createBrowserRouter } from 'react-router';
import { RouterProvider } from 'react-router/dom';

// import { paths } from '@/config/paths';
// import { ProtectedRoute } from '@/lib/auth';

const convert = (queryClient: QueryClient) => (m: any) => {
    const { clientLoader, clientAction, default: Component, ...rest } = m;
    return {
      ...rest,
      loader: clientLoader?.(queryClient),
      action: clientAction?.(queryClient),
      Component,
    };
  };

  export const createAppRouter = (queryClient: QueryClient) =>
    createBrowserRouter([
      {
        path: paths.auth.login.path,
        lazy: () => import('./routes/auth/login').then(convert(queryClient)),
      },
    ]);

export const AppRouter = () => {
    const queryClient = useQueryClient();
      
    const router = useMemo(() => createAppRouter(queryClient), [queryClient]);
      
    return <RouterProvider router={router} />;
  };
      