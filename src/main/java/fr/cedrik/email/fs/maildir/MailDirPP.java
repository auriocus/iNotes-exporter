/**
 *
 */
package fr.cedrik.email.fs.maildir;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.sun.mail.imap.protocol.BASE64MailboxEncoder;

import fr.cedrik.email.FoldersList;
import fr.cedrik.email.spi.PropertiesFileSupplier;
import fr.cedrik.email.spi.SessionSupplier;
import fr.cedrik.inotes.Folder;

/**
 * @author C&eacute;drik LIME
 */
public class MailDirPP extends BaseMailDir {
	protected File baseMailDir;

	public MailDirPP() throws IOException {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		new MailDirPP().run(args, null);
	}

	/**
	 * @param args
	 */
	@Override
	public void _main(String[] args) throws IOException {
		main(args);
	}

	@Override
	protected void help() {
		System.out.println("Usage: "+MailDirPP.class.getSimpleName()+" <out_dir>");
	}

	@Override
	protected boolean prepareDestinationObjects(String baseName, String extension) {
		return super.prepareDestinationObjects(baseName, extension);
	}

	@Override
	protected void run(String[] args, String extension) throws IOException {
		if (args.length != 1) {
			help();
			System.exit(-1);
		}
		if (! prepareDestinationObjects(args[0], extension)) {
			return;
		}
		baseMailDir = new File(args[0]);
		assert writer != null;
		email = PropertiesFileSupplier.Util.get();
		email.setCurrentFolderId(email.getDefaultFolderId());
		session = SessionSupplier.Util.get(email);
		// login
		if (! session.login(email.getUserName(), email.getUserPassword())) {
			logger.error("Can not login user {}!", email.getUserName());
			return;
		}
		try {
			// export folders hierarchy
			FoldersList folders = session.getFolders();
			//FIXME check no 2 folders have the same .name hierarchy
			for (Folder folder : folders) {
				if (folder.getName().startsWith(".")) {
					throw new IllegalArgumentException("Folder can not start with a '.': " + folder);
				}
				if (folder.isAllMails()) {
					continue;
				}
				if (! prepareDestinationObjects(computeMaildirFolderName(folder, folders), extension)) {
					continue;
				}
				export(folder, args);
			}
		} finally {
			session.logout();
		}
	}

	protected String computeMaildirFolderName(Folder folder, FoldersList folders) {
		if (folder.isInbox()) {
			return baseMailDir.getPath();
		} else {
			// compute MailDir++ full folder name and encode each segment
			List<Folder> foldersChain = folders.getFoldersChain(folder);
			StringBuilder result = new StringBuilder();
			result.append('.');
			for (Folder parent : foldersChain) {
				result.append(encodeFolderName(parent.getName()));
				result.append('.');
			}
			result.deleteCharAt(result.length() - 1); // remove trailing '.'
			return new File(baseMailDir, result.toString()).getPath();
		}
	}

	/**
	 * @see RFC2060 5.1.3.  Mailbox International Naming Convention  + special treatment for '.' and '/' as per MailDir++ specification
	 */
	protected String encodeFolderName(String folderName) {
		return BASE64MailboxEncoder.encode(folderName).replace(".", "&AC4-").replace("/", "&AC8-");
	}
}
