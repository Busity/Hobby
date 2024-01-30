package com.Buska.demo.Entity.EntityDTO;


import com.Buska.demo.Entity.Alkalmazott;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Data
public class AlkalmazottDTO {


  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Integer id;
  @NotBlank(message = "The name field cannot be empty")
  @Size(min = 2, max = 50, message = "The length of the name must be between 2 and 50 characters")
  private String nev;
  @NotBlank(message = "The address field cannot be empty")
  @Size(max = 50, message = "The maximum length of the address field can be 50 characters")
  private String lakcim;
  @Column(columnDefinition = "TIMESTAMP")
  private LocalDate szuletesi_Datum;
  @Size(min = 8, max = 20, message = "The Employee's phone number must be between 4 and 20 characters")
  @Pattern(regexp = "^[0-9]{0,9}$", message = "The phone number can only contain numbers between 0 and 9")
  private String telefonszam;
  @NotBlank(message = "The email address cannot be empty")
  @Email(message = "Invalid email address format")
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
  private String email;
  private boolean deleted;

  public AlkalmazottDTO(Integer id, String nev, String lakcim, LocalDate szuletesi_Datum,
      String telefonszam, String email, boolean deleted) {
    this.id = id;
    this.nev = nev;
    this.lakcim = lakcim;
    this.szuletesi_Datum = szuletesi_Datum;
    this.telefonszam = telefonszam;
    this.email = email;
    this.deleted = deleted;
  }

  public AlkalmazottDTO() {
  }

  public AlkalmazottDTO(Alkalmazott alkalmazott) {
    if (alkalmazott != null) {
      this.id = alkalmazott.getId();
      this.nev = alkalmazott.getNev();
      this.lakcim = alkalmazott.getLakcim();
      this.szuletesi_Datum = alkalmazott.getSzuletesi_Datum();
      this.telefonszam = alkalmazott.getTelefonszam();
      this.email = alkalmazott.getEmail();
      this.deleted = alkalmazott.isDeleted();
    } else {
      this.id = null;
      this.nev = null;
      this.lakcim = null;
      this.szuletesi_Datum = null;
      this.telefonszam = null;
      this.email = null;
      this.deleted = false;
    }
  }

  public AlkalmazottDTO(AlkalmazottDTO alkalmazottDTO) {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNev() {
    return nev;
  }

  public void setNev(String nev) {
    this.nev = nev;
  }

  public String getLakcim() {
    return lakcim;
  }

  public void setLakcim(String lakcim) {
    this.lakcim = lakcim;
  }

  public LocalDate getSzuletesi_Datum() {
    return szuletesi_Datum;
  }

  public void setSzuletesi_Datum(LocalDate szuletesi_Datum) {
    this.szuletesi_Datum = szuletesi_Datum;
  }

  public String getTelefonszam() {
    return telefonszam;
  }

  public void setTelefonszam(String telefonszam) {
    this.telefonszam = telefonszam;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }


}