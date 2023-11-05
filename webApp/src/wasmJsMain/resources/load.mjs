import { instantiate } from './spiragps.uninstantiated.mjs';

await wasmSetup;

instantiate({ skia: Module['asm'] });
