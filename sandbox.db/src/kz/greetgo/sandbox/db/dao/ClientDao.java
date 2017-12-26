package kz.greetgo.sandbox.db.dao;

import kz.greetgo.sandbox.controller.model.CharmRecord;
import kz.greetgo.sandbox.controller.model.ClientAddress;
import kz.greetgo.sandbox.controller.model.ClientDetails;
import kz.greetgo.sandbox.controller.model.ClientRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ClientDao {


  @Select("select * from Charm where actual = 1 order by name")
  List<CharmRecord> loadCharmList();

  @Select("select c.charm_id charmId," +
    " c.birth_date dateOfBirth," +
    " c.current_gender gender," +
    " c.* from Client c" +
    " where id = #{id} and" +
    " c.actual = 1")
  ClientDetails loadDetails(@Param("id") String id);

  @Update("update client set actual = 0 where id = #{id}")
  void deleteClient(@Param("id") String id);

  @Update("update client_addr set actual = 0 where client = #{id}")
  void deleteClientAddress(@Param("id") String id);

  @Update("update client_phone set actual = 0 where client = #{id}")
  void deleteClientPhone(@Param("id") String id);

  @Update("update client_account set actual = 0 where id = #{id}")
  void deleteClientAccount(@Param("id") String id);

  @Update("update client set ${fieldName} = #{value} where id = #{clientId}")
  void updateClientField(@Param("clientId") String id, @Param("fieldName") String fieldName,
                         @Param("value") Object value);


  @Insert("insert into client (id, name, surname, patronymic, birth_date, current_gender, charm_id, actual) " +
    "values (#{id}, #{name}, #{surname}, #{patronymic}, #{birth_date}, #{gender}, #{charm_id}, 1); ")
  void insertClient(@Param("id") String id,
                    @Param("name") String name,
                    @Param("surname") String surname,
                    @Param("patronymic") String patronymic,
                    @Param("birth_date") java.sql.Date birthDate,
                    @Param("gender") String gender,
                    @Param("charm_id") String charmId);


  @Select("select c.id as id, " +
    " c.surname || ' ' || c.name || ' ' || c.patronymic AS fio, " +
    " ch.name AS charm, " +
    " extract(year from age(c.birth_date)) as age " +
    " from client c join charm ch on (c.charm_id = ch.id) " +
    " where c.actual = 1 and ch.actual = 1 " +
    " and c.id = #{id}")
  ClientRecord getClientRecord(@Param("id") String id);


  @Update("update client_addr set ${fieldName} = #{value} where client = #{id} and type = #{type}")
  void updateAddressField(@Param("id") String id,
                          @Param("type") String type,
                          @Param("fieldName") String fieldName,
                          @Param("value") Object value);

  @Select("select street, house, flat from client_addr where client = #{id} and type = 'fact' and actual = 1")
  ClientAddress getFactAddress(@Param("id") String id);

  @Select("select street, house, flat from client_addr where client = #{id} and type = 'reg' and actual = 1")
  ClientAddress getRegAddress(String id);

  @Insert("insert into client_addr(client, street, house, flat, type, actual) " +
    "values (#{id}, #{street}, #{house}, #{flat}, #{type}, 1)")
  void insertClientAddress(@Param("id") String id,
                           @Param("street") String street,
                           @Param("house") String house,
                           @Param("flat") String flat,
                           @Param("type") String type);

  @Insert("insert into client_phone(client, number, type, actual)" +
    " values (#{id}, #{number}, #{type}, 1)" +
    " on conflict(client, number) do update" +
    " set actual = 1, type = #{type}")
  void insertClientPhone(@Param("id") String id,
                         @Param("number") String number,
                         @Param("type") String type);

  @Select("select number from client_phone where type = 'home' and client = #{id} and actual = 1")
  String getHomePhone(@Param("id") String id);

  @Select("select number from client_phone where type = 'work' and client = #{id} and actual = 1")
  String getWorkPhone(@Param("id") String id);

  @Select("select number from client_phone where type = 'mobile' and client = #{id} and actual = 1")
  List<String> getMobilePhone(String id);

}