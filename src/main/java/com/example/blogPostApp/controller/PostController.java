package com.example.blogPostApp.controller;


/*public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
   // private AccountService accountService;

  @GetMapping("/posts/{id}")
  public String getPost(@PathVariable Long id, Model model) {
      Optional<Post> optionalPost = postService.getById(id);
      if (optionalPost.isPresent()) {
          Post post = optionalPost.get();
          model.addAttribute("post", post);
          return "post";
      } else {
          return "post1";
      }
  }

 /*   @GetMapping("/posts/new")
    public String createNewPost(Model model){
        Optional<Account> optionalAccount = accountService.findByEmail("user@email.com");
        if(optionalAccount.isPresent()){
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post",post);

            return "post_new.html";
        }else{
            return "post1";
        }
    }*/
   /* @PostMapping("/posts/new")
    public String saveNewPost(@ModelAttribute Post post){
        postService.save(post);
        return "redirect:/posts/" + post.getId();
    }

}*/


