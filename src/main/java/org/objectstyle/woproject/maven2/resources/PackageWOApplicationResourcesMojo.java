package org.objectstyle.woproject.maven2.resources;

//org.apache.maven.plugins:maven-compiler-plugin:compile
import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

/**
 * resources goal for WebObjects projects.
 * 
 * @goal package-woapplication
 * @requiresDependencyResolution compile
 * @author uli
 * @since 2.0
 */
public class PackageWOApplicationResourcesMojo extends DefineResourcesMojo {

	/**
	 * The maven project.
	 * 
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * The set of dependencies required by the project
	 * 
	 * @parameter default-value="${project.dependencies}"
	 * @required
	 * @readonly
	 */
	private java.util.ArrayList dependencies;

	public PackageWOApplicationResourcesMojo() throws MojoExecutionException {
		super();
	}
	
	public MavenProject getProject() {
		return project;
	}

	public String getProductExtension() {
		return "woa";
	}

	protected String getArtifactFileName() {
		return "target" + File.separator + this.getProject().getArtifactId()
				+ "-" + this.getProject().getVersion() + ".tar.gz";
	}
}
