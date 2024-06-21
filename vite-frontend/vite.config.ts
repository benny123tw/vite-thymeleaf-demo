import { defineConfig } from 'vite'
import java, { createRollupInputConfig } from 'vite-plugin-java'
import tsconfigPaths from 'vite-tsconfig-paths'

export default defineConfig({
  build: {
    outDir: '../src/main/resources/build',
    emptyOutDir: true,
  },
  plugins: [tsconfigPaths(), java(
      {
        javaProjectBase: '../',
        publicDirectory: 'public',
        buildDirectory: 'resources',
        input: createRollupInputConfig('src/**/main.ts', 'src'),
      },
  )],
})
