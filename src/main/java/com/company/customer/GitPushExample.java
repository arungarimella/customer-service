package com.company.customer;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;

public class GitPushExample {
	public static void main(String[] args) {
		// Path to the local Git repository
		String localRepoPath = "/path/to/local/repo";

		// Credentials for authentication (if needed)
		String username = "your_username";
		String password = "your_password";

		// Remote repository URL
		String remoteRepoUrl = "https://github.com/username/repository.git";

		// Files to be added and committed
		String[] filesToAdd = { "/path/to/file1", "/path/to/file2" };

		try {
			// Open the local repository
			Git git = Git.open(new File(localRepoPath));

			// Add files
			for (String file : filesToAdd) {
				git.add().addFilepattern(file).call();
			}

			// Commit
			git.commit().setMessage("Added files").call();

			// Set up credentials provider if authentication is required
			CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(username, password);

			// Push
			git.push().setCredentialsProvider(credentialsProvider) // Set credentials provider
					.setRemote(remoteRepoUrl) // Set remote repository URL
					.call();

			System.out.println("Files pushed successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void featureBranch() {
		// Path to the local Git repository
		String localRepoPath = "/path/to/local/repo";

		// Credentials for authentication (if needed)
		String username = "your_username";
		String password = "your_password";

		// Remote repository URL
		String remoteRepoUrl = "https://github.com/username/repository.git";

		// Name of the feature branch
		String featureBranchName = "feature-branch";

		try {
			// Open the local repository
			Git git = Git.open(new File(localRepoPath));

			// Checkout to the base branch (e.g., master)
			git.checkout().setName("master").call();

			// Pull latest changes from remote repository
			git.pull().call();

			// Create a new feature branch
			Ref branchRef = git.branchCreate().setName(featureBranchName).call();

			// Set up credentials provider if authentication is required
			CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(username, password);

			// Push the newly created branch to the remote repository
			git.push().setCredentialsProvider(credentialsProvider) // Set credentials provider
					.setRemote(remoteRepoUrl) // Set remote repository URL
					.add(branchRef) // Add the reference of the newly created branch
					.call();

			System.out.println("Feature branch created and pushed successfully.");

		} catch (GitAPIException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
