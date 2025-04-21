import { createBrowserRouter } from 'react-router';
import { RouterProvider } from 'react-router';
import { useMemo } from 'react';
import { paths } from '@/config/paths';

const convert = (m: any) => {
  const { clientLoader, clientAction, default: Component, ...rest } = m;
  return {
    ...rest,
    loader: clientLoader?.(),
    action: clientAction?.(),
    Component,
  };
};

const createAppRouter = () =>
  createBrowserRouter([
    {
      path: paths.auth.login.path,
      lazy: () => import('./routes/auth/login').then(convert),
    },
    {
      path: paths.home.path,
      lazy: () => import('./routes/app/dashboard').then(convert),
    },
  ]);

export const AppRouter = () => {
  const router = useMemo(() => createAppRouter(), []);
  return <RouterProvider router={router} />;
};
