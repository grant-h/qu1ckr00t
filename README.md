# qu1ckr00t
A PoC application demonstrating the power of an Android kernel arbitrary R/W (CVE-2019-2215). Writeup: https://hernan.de/blog/2019/10/15/tailoring-cve-2019-2215-to-achieve-root/

**Qu1ckR00t is a PROOF OF CONCEPT. It should NOT be used on your personal device with valuable userdata. It has only been tested on a Pixel 2. Running it on any other device / kernel will likely lead to a crash or even data loss. DO NOT install extra Magisk environment files or upgrade Magisk if prompted as this will patch boot, breaking DM-Verity on next boot likely leading to data-loss when you need to reflash.**

No prebuilt APKs are provided to avoid people messing up their device. Build and customize it to your specific device!

## Notes
* The exploit for CVE-2019-2215 is at [native/poc.c](https://github.com/grant-h/qu1ckr00t/blob/master/native/poc.c). Compile this with the Android NDK.
* Native binaries (Magisk + exploit) are bundled into the APK in [app/src/main/res/raw](https://github.com/grant-h/qu1ckr00t/tree/master/app/src/main/res/raw). Add or replace these with device-specific code.
* The YOLO-installer&trade; for Magisk is at [app/src/main/res/raw/magisk_install](https://github.com/grant-h/qu1ckr00t/blob/master/app/src/main/res/raw/magisk_install) and has only been tested on a AArch64 Pixel 2 running Android Q. YMMV.

## Limitations
* Magisk was never meant to be installed without a patched boot image
* Magisk install is core-mode only
* Magisk app SU notifications don't appear to be working due to the `request` intent not making it. I manually sent it during the SU timeout window using ADB and the command: `am start -n APP_ID/a.m --user 0 -f 0x18000020 -a request --es socket SOCKET_ID`, where APP_ID is the package name of the install magisk manager and SOCKET_ID is the listening socket of the `magisk` daemon (found using `lsof | grep magisk | grep ' @'` in a root shell)

## Related
* https://github.com/kangtastic/cve-2019-2215/blob/master/cve-2019-2215.c
