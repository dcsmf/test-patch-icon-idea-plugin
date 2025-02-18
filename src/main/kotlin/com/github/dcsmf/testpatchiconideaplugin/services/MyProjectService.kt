package com.github.dcsmf.testpatchiconideaplugin.services

import com.github.dcsmf.testpatchiconideaplugin.patchers.MyIconPatchers
import com.intellij.ide.projectView.ProjectView
import com.intellij.ide.ui.LafManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.util.IconLoader

@Service(Service.Level.APP)
class MyProjectService {

    init {
        IconLoader.installPathPatcher(MyIconPatchers.instance)
    }

    fun patchIcon():Boolean {
        IconLoader.clearCache()
        MyIconPatchers.instance.enable = !MyIconPatchers.instance.enable
        LafManager.getInstance().updateUI()
        val projects: Array<Project> = ProjectManager.getInstance().openProjects
        for (project in projects) refresh(project)
        return MyIconPatchers.instance.enable
    }

    private fun refresh(project: Project?) {
        if (project != null) {
            val view = ProjectView.getInstance(project)
            if (view != null) {
                view.refresh()
                if (view.currentProjectViewPane != null) view.currentProjectViewPane.updateFromRoot(true)
            }
        }
    }

}
