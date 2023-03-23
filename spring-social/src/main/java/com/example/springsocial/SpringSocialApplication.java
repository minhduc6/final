package com.example.springsocial;

import com.example.springsocial.config.AppProperties;
import com.example.springsocial.model.*;
import com.example.springsocial.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringSocialApplication {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private  EventCategoryRepository eventCategoryRepository;
	@Autowired
	private  InvoiceDetailRepository invoiceDetailRepository;

	@Autowired
	private  InvoiceRepository invoiceRepository;

	@Autowired
	private OrganizersRepository organizersRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private TypeTicketRepository typeTicketRepository;


	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSocialApplication.class, args);
	}

	@PostConstruct
	public void init() {
		Role role = Role.builder().roleId(2L).name("ROLE_ADMIN").build();
		Role role1 = Role.builder().roleId(1L).name("ROLE_USER").build();

		Set<Role> roles = new HashSet<>();
		roles.add(role);
		roles.add(role1);
		roleRepository.saveAll(roles);

		User user = User.builder().id(1L).providerUserId("123").email("duc123@gmail.com").displayName("junior")
				.password(passwordEncoder.encode("123"))
				.imgUrl("image.jpg").enabled(true).statusLogin(1).roles(roles).build();
		userRepository.save(user);

		Organizers organizers = Organizers.builder().name("DUC EVENT")
				.id(1L)
				.codeBusiness("123")
				.email("duc123@gmail.com")
				.image("Image.jpg")
				.city("HaNoi")
				.district("HBT")
				.ward("BachMai")
				.user(userRepository.findById(1L).get())
				.build();
		organizersRepository.save(organizers);

		Category category1 = Category.builder().id(1L).name("Concert").description("Live").build();
		Category category2 = Category.builder().id(2L).name("123").description("Live1").build();
		Category category4 = Category.builder().id(4L).name("Concert1").description("Live3").build();

		Set<Category> categories1 = new HashSet<>();
		categories1.add(category1);
		categories1.add(category2);
		categories1.add(category4);
		categoryRepository.saveAll(categories1);


		Event event = Event.builder()
				.id(1L)
				.name("Hà Anh Tuấn Concert")
				.description("Siêu Phẩm")
				.address("NinhBinh")
				.status(1)
				.organizer(organizers)
				.build();
		Event event1 = Event.builder()
				.id(2L)
				.name("Hoàng Dũng Concert")
				.description("Siêu Phẩm")
				.address("HaNoi	")
				.status(1)
				.organizer(organizers)
				.build();
		eventRepository.save(event);
		eventRepository.save(event1);

		EventCategoryId eventCategoryId = EventCategoryId.builder().eventId(event.getId()).categoryId(category1.getId()).build();
		EventCategory eventCategory = EventCategory.builder().id(eventCategoryId).event(event).category(category1).build();
		eventCategoryRepository.save(eventCategory);


		EventCategoryId eventCategoryId1 = EventCategoryId.builder().eventId(event1.getId()).categoryId(category2.getId()).build();
		EventCategory eventCategory1 = EventCategory.builder().id(eventCategoryId1).event(event1).category(category2).build();
		eventCategoryRepository.save(eventCategory1);

		TypeTicket t1 = TypeTicket.builder().id(1L).name("Hạng A").description("Full Options").price(20000).quantity(20)
				.event(event1).build();
		TypeTicket t2 = TypeTicket.builder().id(2L).name("Hạng B").description("FULL-1 Options").price(10000)
				.quantity(20).event(event1).build();
		typeTicketRepository.save(t1);
		typeTicketRepository.save(t2);


		Invoice invoice = Invoice.builder().id(1L).times(LocalDateTime.now()).amount(20000f).status(1).userOrder(user).build();
		invoiceRepository.save(invoice);
		InvoiceDetailId invoiceDetailId = InvoiceDetailId.builder().invoiceId(invoice.getId()).ticketId(t1.getId()).build();
		InvoiceDetail invoiceDetail = InvoiceDetail.builder().id(invoiceDetailId).invoice(invoice).typeTicket(t1).build();
		invoiceDetailRepository.save(invoiceDetail);



		Invoice invoice1 = Invoice.builder().id(2L).times(LocalDateTime.now()).amount(30000f).status(1).userOrder(user).build();
		invoiceRepository.save(invoice1);
		InvoiceDetailId invoiceDetailId1 = InvoiceDetailId.builder().invoiceId(invoice1.getId()).ticketId(t2.getId()).build();
		InvoiceDetail invoiceDetail1 = InvoiceDetail.builder().id(invoiceDetailId1).invoice(invoice1).typeTicket(t2).build();
		invoiceDetailRepository.save(invoiceDetail1);
	}
}
