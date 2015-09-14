package cl.doman.db.model.table;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class FileTable extends StandardTable implements cl.doman.db.model.table.BaseTable<Integer>,Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	public Integer getId() {
		return this.id;
	}

	@Lob
	private byte[] content;

	private String filename;

	private String mimetype;

	public FileTable() {
	}


	public byte[] getContent() {
		return this.content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMimetype() {
		return this.mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}
}
