package com.github.dcsmf.testpatchiconideaplugin.patchers

import com.intellij.openapi.util.IconPathPatcher

class MyIconPatchers : IconPathPatcher() {

    var enable = false

    /**
     * Patches the path or returns `null` if nothing has patched.
     *
     * @param path        path to the icon
     * @param classLoader ClassLoader of the icon is requested from
     * @return patched path or `null`
     */
    override fun patchPath(path: String, classLoader: ClassLoader?): String? {
        if (!enable) return null
        if (path.contains("windowsMenu")) {
            return "icons/windowsMenu_dark.svg"
        }
        return null
    }

    /**
     * Return ClassLoader for icon path or `null` if nothing has patched.
     *
     * @param path                path to the icon
     * @param originalClassLoader ClassLoader of the icon is requested from
     * @return patched icon ClassLoader or `null`
     */
    override fun getContextClassLoader(path: String, originalClassLoader: ClassLoader?): ClassLoader? {
        if (!enable) return null
        return javaClass.classLoader
    }

    companion object {
        val instance = MyIconPatchers()
    }

}