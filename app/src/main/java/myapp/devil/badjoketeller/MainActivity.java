package myapp.devil.badjoketeller;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;

import myapp.devil.badjoketeller.R;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button oneMore;
    ImageButton exit, shareButton, privacyPolicyButton;
    TextView txt1, txt2;
    int r = 0;
    Integer[] arr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);

        oneMore = findViewById(R.id.oneMore);
        shareButton = findViewById(R.id.shareButton);
        privacyPolicyButton = findViewById(R.id.privacyPolicy);
        exit = findViewById(R.id.imageExitButton);
        txt1 = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView2);

        txt1.setText("Welcome to the world of hilariously bad jokes.\n\n Hope you enjoy them!");
        txt2.setText(" ");

        oneMore.setOnClickListener(this);
        shareButton.setOnClickListener(this);
        privacyPolicyButton.setOnClickListener(this);
        exit.setOnClickListener(this);

        oneMore.setText(R.string.ready);

        arr = new Integer[jokesArray.length];
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = i;
        }
        Collections.shuffle(Arrays.asList(arr));
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == shareButton.getId())
        {
            if(oneMore.getText().toString().equalsIgnoreCase("I'm Ready!"))
            {
                shareJoke(-1);
            }
            else
            {
                shareJoke(arr[r-1]);
            }
        }
        else if (v.getId() == oneMore.getId())
        {
            getJoke(arr[r]);
            r++;
        }
        else if (v.getId() == privacyPolicyButton.getId())
        {
            Intent intent = new Intent(getApplicationContext(),
                    PrivacyPolicyActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == exit.getId())
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Exit");
            builder.setMessage("Sure want to exit?");

            builder.setPositiveButton("Yes. Exit now", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }
            );

            builder.setNegativeButton("No. More jokes!", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {dialogInterface.dismiss();}

                    }
            );
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    void getAns(int r)
    {
        txt2.setTextSize(25);
        txt2.setClickable(false);
        txt2.setEnabled(false);
        txt2.setText(jokesArray[r][1]);
        txt2.setOnTouchListener(null);
        oneMore.setVisibility(View.VISIBLE);
    }

    @SuppressLint("ClickableViewAccessibility")
    void getJoke(final int r)
    {
        if(jokesArray[r][1].equals("0"))
        {
            txt1.setText(jokesArray[r][0]);
            txt2.setText("");
            oneMore.setText(R.string.oneMore);
        }

        if(!jokesArray[r][1].equals("0"))
        {
            txt1.setText(jokesArray[r][0]);
            txt2.setTextSize(60);
            txt2.setText("?");
            txt2.setClickable(true);
            txt2.setEnabled(true);

            txt2.setOnTouchListener(new View.OnTouchListener()
            {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(txt2.isClickable()){
                        getAns(r);
                        return true;
                    }
                    return false;
                }
            });
            oneMore.setText(R.string.oneMore);
            oneMore.setVisibility(View.INVISIBLE);
        }
    }

    void shareJoke(int r)
    {
        String msg;
        if(r == -1)
        {
            msg = "Hey! Check out this app:";
        }
        else if (jokesArray[r][1].equals("0"))
        {
            msg = jokesArray[r][0]+ "\n\n:D :D\nFor more hilarious jokes, download this app: ";
        }
        else
        {
            msg = jokesArray[r][0] + "\n" +".\n"+".\n"+".\n" + jokesArray[r][1] + "\n\n:D :D\nFor more hilarious jokes, download this app: ";
        }

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent,"Share!");
        startActivity(shareIntent);
    }

    public String[][] jokesArray =
            {
                    { "If you’re American when you go in the bathroom and American when you come out, what are you in the bathroom?", "European" },
                    { "What did the fish say when he swam into a wall?", "Dam" },
                    {"What do you call a fish with no eyes?","A fsh"},
                    {"What do you call a can opener that doesn’t work?","A can’t opener!"},
                    {"What do you get when you combine a rhetorical question and a joke?","…"},
                    {"There are three types of people in the world:","Those who can count and those who can’t."},
                    {"Did you hear about the Italian chef who died?","He pasta-way."},
                    {"Two muffins were sitting in an oven.","One turned to the other and said, “Wow, it’s pretty hot in here.” The other one shouted, “Wow, a talking muffin!”"},
                    {"I sold my vacuum the other day.","All it was doing was collecting dust."},
                    {"What is Forrest Gump’s email password?","1forrest1"},
                    {"Did you hear about the guy who invented the knock knock joke?","He won the “no-bell” prize."},
                    {"Did you hear about the fire in the shoe factory?","10,000 soles were lost. The police said some heels started it."},
                    {"What’s the difference between a rabbit and a plum?","They’re both purple except for the rabbit."},
                    {"Two windmills are standing in a wind farm.","One asks, “What’s your favorite type of music?” The other says, “I’m a big metal fan.”"},
                    {"I like elephants.","Everything else is irrelephant."},
                    {"What’s red and bad for your teeth?","A brick."},
                    {"Two guys walk into a bar.","The third guy ducks."},
                    {"What do you call a fake noodle?","An impasta."},
                    {"Why does Snoop Dogg use an umbrella?","For drizzle."},
                    {"Did you hear the story about the claustrophobic astronaut?","He just needed some space."},
                    {"What do you call an alligator in a vest?","An in-vest-igator."},
                    {"What kind of tea is hard to swallow?","Reality."},
                    {"A man and a giraffe walk into a bar. After a few drinks, the giraffe falls over and dies. The man begins to walk out when the bartender stops him. Hey, you can’t leave that lyin’ there!” The bartender yells out. The man turns around: “It’s not a lion. It’s a giraffe."},
                    {"Why can’t a nose be 12 inches long?","Because then it’d be a foot."},
                    {"The wedding was so beautiful.","Even the cake was in tiers."},
                    {"Why don’t dinosaurs talk?","Because they’re dead."},
                    {"A dyslexic man walks into a bra.","0"},
                    {"What do you call a fly with no wings?","A walk."},
                    {"What did the mime say to his audience?","Nothing. He held his character because he’s a professional."},
                    {"What did the Buddhist say to the hot dog vendor?","Make me one with everything."},
                    {"What did the janitor say when he jumped out of the closet?","“Supplies!”"},
                    {"It’s inappropriate to make a “dad joke” if you are not a dad.","It’s a faux pa."},
                    {"What did the buffalo say when his son left?","Bison!"},
                    {"What’s green and has wheels?","Grass. I lied about the wheels."},
                    {"My new thesaurus is terrible.","Not only that, but it’s also terrible."},
                    {"What do you call a man with no arms and no legs in a pool?","Bob."},
                    {"What do you call a psychic little person who has escaped from prison?","A small medium at large."},
                    {"What’s the most terrifying word in nuclear physics?","“Oops!”"},
                    {"What did Blackbird say when he turned 80?","“Aye, matey.”"},
                    {"Three fish are in a tank.","One asks the others, “How do you drive this thing?”"},
                    {"What’s the dumbest animal in the jungle?","A polar bear."},
                    {"What do you call a man who can’t stand?","Neil."},
                    {"I used to be addicted to the hokey pokey…","… but then I turned myself around."},
                    {"Did you know the first French fries weren’t actually cooked in France?","They were cooked in Greece."},
                    {"I don’t trust stairs.","They’re always up to something."},
                    {"Wife: “How do I look?”","Husband: “With your eyes.”"},
                    {"What’s the best part about living in Switzerland?","I don’t know, but the flag is a big plus."},
                    {"Have you heard the rumor about butter?","Never mind, I shouldn’t be spreading it."},
                    {"What do you call someone with no body and no nose?","Nobody knows."},
                    {"How did the dead brother and his dead brother resemble each other?","They were dead ringers."},
                    {"What kind of a shot was the dead man?","He had dead aim."},
                    {"What kind of personality did the dead man have?","He gave you the cold shoulder."},
                    {"Why were the people trying to get the dead man to change his mind?","Because they knew he was dead wrong."},
                    {"Why was the dead man not courageous?","Because he had cold feet."},
                    {"Why was the dead man insensitive?","Because he had a cold heart."},
                    {"Why don’t pirates take a shower before they walk the plank?","They just wash up on shore."},
                    {"How much did the pirate pay for his peg and hook?","An arm and a leg."},
                    {"How long does it take to know if a pair of underwear fits you well?","Just a brief moment!"},
                    {"Why should you sit in a corner when you get cold?","Because most corners are 90 degrees."},
                    {"I used to work for an origami company until it folded.","0"},
                    {"I remember how embarrassed I was when I couldn’t pay my electric bill.  It was the darkest day of my life.","0"},
                    {"Sometimes I tuck my knees into my chest and lean forward.","That’s just how I roll."},
                    {"What did one toilet say to the other toilet?","You look flushed."},
                    {"What’s the best time to go to the dentist?","Tooth hurty."},
                    {"Where do beef burgers go to dance?","The meatball."},
                    {"Which side of a duck has the most feathers?","The outside."},
                    {"Where do Volkswagens go when they get old?"," The old Volks home."},
                    {"What do a dog and a phone have in common?","They both have collar ID."},
                    {"What did the red light say to the green light?","Don’t look, I’m changing."},
                    {"What do you call a T-Rex that’s been beaten up?","Dino-sore."},
                    {"What did the axe murderer say to the judge?","It was an axe-ident."},
                    {"How much does a Mustang cost?","More than you can af-Ford."},
                    {"What did the policeman say to his belly button?","You’re under a vest."},
                    {"What do you call someone who plays tricks on Halloween?","Prankenstein."},
                    {" What do you call a baby monkey?"," A chimp off the old block."},
                    {"Why did the pig get hired by the restaurant?","He was really good at bacon."},
                    {"What do you call anxious dinosaurs?","Nervous Rex."},
                    {"What did the fisherman say to the magician?","Pick a cod, any cod."},
                    {"What’s an astronaut’s favorite part of a computer?","The space bar."},
                    {"Why did the poor man sell yeast?","To raise some dough."},
                    {"Why was the belt sent to jail?","For holding up a pair of pants!"},
                    {"Do you think glass coffins will be a success?","Well, it remains to be seen."},
                    {"Two fish are in a tank. One says to the other, “Do you know how to drive this thing?”","0"},
                    {"Why did the invisible man turn down the job offer?","Because he just couldn’t see himself doing it."},
                    {"What happened when one cannibal arrived late to the dinner party?","The others gave him the cold shoulder."},
                    {"A burglar stole all the lamps in my house. I know I should be more upset, but I’m absolutely delighted.","0"},
                    {"Don't know why people always tell me I’m condescending.\n" +"\n" +"(That means talking down to people.)","0"},
                    {"Bifocals are God’s way of saying, “Keep your chin up.”","0"},
                    {"Mary goes to the post office to buy 50 stamps for her Hanukkah cards. “What denomination?” asks the postal clerk.","Mary thinks a second before replying, “Give me six Orthodox, 12 Conservative, and 32 Reform.”"},
                    {"There is no way that you'll be late for anything in London.. They have a huge clock right in the middle of the town.","0"},
                    {"Nature abhors a vacuum, but not as much as a cat does.","0"},
                    {"Here’s some advice: At a job interview, tell them you’re willing to give 110 percent. Unless the job is a statistician.","0"},
                    {"So what if I can’t spell Armageddon? It’s not the end of the world.","0"},
                    {"“Pickup artists” and “garbagemen” should switch names.","0"},
                    {"WHAT DID THE SCHIZOPHRENIC BOOKKEEPER SAY?","I hear invoices!"},
                    {"At what age do you think it’s appropriate to tell a highway it’s adopted?","0"},
                    {" Why did the chicken go to the séance?","To get to the other side."},
                    {"A zookeeper is ordering new animals. As he fills out the forms, he types “two mongeese.” That doesn’t look right, so he tries “two mongoose,” then “two mongooses.” Giving up, he types, “One mongoose, and while you’re at it, send another one.”","0"},
                    {"I got my hair highlighted because I thought some strands were more important than others.","0"},
                    {"A farmer counted 196 cows in the field. But when he rounded them up, he had 200.","0"},
                    {"WHERE ARE AVERAGE THINGS MANUFACTURED?","The satisfactory."},
                    {"HOW DO YOU DROWN A HIPSTER?","Throw him into the mainstream."},
                    {"Karate: the ancient Japanese art of getting people to buy lots of belts.","0"},
                    {"What do you call a hippie's wife?","A Mississippi!"},
                    {"What did the duck say when she bought a lipstick","'Put it on my bill!'"},
                    {"I hate Russian dolls.","They're so full of themselves."},
                    {"What do you call a man with a rubber toe?","Roberto!"},
                    {"Where did the computer go dancing?","The disc-o!"},
                    {"What do bees do if they need a ride?","Wait at the buzz stop!"},
                    {"What do you give to a sick lemon?","Lemon aid!"},
                    {"What did the little mountain say to the bigger mountain?","Hi Cliff!"},
                    {"What do you call a monkey that loves Doritos?","A chipmunk!"},
                    {"Why did the can crusher quit his job?","Because it was soda pressing!"},
                    {"Why are there gates around cemeteries?","Because people are dying to get in!"},
                    {"What do you call a cow with two legs?","Lean beef!"},
                    {"Do you remember that joke I told you about my spine?","It was about a weak back!"},
                    {"What do you call a dangerous sun shower?","A rain of terror!"},
                    {"What do you call a farm that makes bad jokes?","Corny!"},
                    {"Why do fish live in salt water? ","Because pepper makes them sneeze!"},
                    {"What do you tell actors to break a leg? ","Because every play has a cast!"},
                    {"What kind of dogs love car racing?","Lap dogs!"},
                    {"What did Winnie the Pooh say to his agent?","\"Show me the honey!\""},
                    {"What do you call birds who stick together?","Vel-crows."},
                    {"Today I gave my dead batteries away.","They were free of charge."},
                    {"What do you call it when one cow spies on another?","A steak out!"},
                    {"What happens when a frog's car breaks down? ","It gets toad!"},
                    {"I went on a once-in-a-lifetime vacation.","Never again."},
                    {"My favorite word is \"drool.\"","It just rolls off the tongue."},
                    {"Why is Peter Pan always flying?","He neverlands."},
                    {"I just wrote a book on reverse psychology. ","Do not read it."},
                    {"What does a zombie vegetarian eat?","\"Graaaaaaaains!\""},
                    {"I got fired from my job at the bank today. An old lady came in and asked me to check her balance, so I pushed her over.","0"},
                    {"I wasn't going to visit my family this December, but my mom promised to make me Eggs Benedict. So I'm going home for the hollandaise.","0"},
                    {"What did the blanket say as it fell off the bed?","\"Oh sheet!\""},
                    {"I like to spend every day as if it's my last. Staying in bed and calling for a nurse to bring me more pudding.","0"},
                    {"Why do cow-milking stools only have three legs? ","'Cause the cow's got the udder!"},
                    {"How did Darth Vader know what Luke got him for Christmas?"," He felt his presents."},
                    {"What's the last thing that goes through a bug's mind when it hits a windshield?","Its butt."},
                    {"Imagine if Americans switched from pounds to kilograms overnight.","There would be mass confusion!"},
                    {"I have an addiction to cheddar cheese. ","But it's only mild."},
                    {"Why shouldn't you write with a broken pencil? ","Because it's pointless!"},
                    {"Why did the scarecrow win an award? ","He was outstanding in his field"},
                    {"I was sitting in traffic the other day.","Probably why I got run over."},
                    {"What's red and shaped like a bucket?","A blue bucket painted red."},
                    {"Why do you smear peanut butter on the road? ","To go with the traffic jam."},
                    {"When is your door not actually a door? ","When it's actually ajar."},
                    {"My grandfather has the heart of a lion and a lifetime ban from the National Zoo.","0"},
                    {"What's green, fuzzy, and would hurt if it fell on you out of a tree?","A pool table."},
                    {"A communist joke isn't funny unless everyone gets it.","0"},
                    {"What did one dish say to the other? ","Dinner is on me!"},
                    {"What does a house wear?","Address!"},
                    {"Why can't you hear a Pterodactyl go to the bathroom?","Because the pee is silent."},
                    {"Cosmetic surgery used to be such a taboo subject. Now you can talk about Botox and nobody raises an eyebrow.","0"},
                    {"What do you call someone who immigrated to Sweden?","Artificial Swedener."},
                    {"Have you heard the one about the corduroy pillow? ","It's making headlines."},
                    {"Have you heard of the band 923 Megabytes? ","Probably not, they haven't had a gig yet."},
                    {"What's the difference between a dirty bus stop and a lobster with breast implants?","One is a crusty bus station and the other is a busty crustacean."},
                    {"There's no hole in your shoe? ","Then how'd you get your foot in it?"},
                    {"Why couldn't the bicycle stand up?","Because it was too tired."},
                    {"A chicken coup only has two doors.","If it had four, it would be a chicken sedan."},
                    {"Why don't crabs donate? ","Because they're shellfish."},
                    {"How does your feline shop? ","By reading a catalogue."},
                    {"It's hard to teach kleptomaniacs humor.","They take things so literally."},
                    {"Sunny-side up, scrambled, or an omelet? ","It doesn't matter. They're all eggcellent."},
                    {"Don't worry if you miss a gym session.","Everything will work out."},
                    {"Ever tried to eat a clock?","It's time-consuming."},
                    {"Who can jump higher than a house?","Pretty much anyone. (Houses can't jump.)"},
                    {"What do an apple and an orange have in common?","Neither one can drive."},
                    {"Why did the businessman invest in Smith & Wollensky?","He wanted to stake his claim."},
                    {"This sweet ride has four wheels and flies.","It's a garbage truck."},
                    {"How many bugs do you need to rent out an apartment?","Tenants."},
                    {"I want to go camping every year.","That trip was so in tents."},
                    {"Wait, you don't want to hear a joke about potassium?","K."},
                    {"How do you organize a space-themed hurrah? ","You planet."},
                    {"Your ex.","That's the punchline."},
                    {"How do you feel when there's no coffee?","Depresso."},
                    {"I broke my arm in two places. You know what the doctor told me?","\"Stay out of those places!\""},
                    {"What do you give a sick bird?","Tweetment."},
                    {"Where did the king keep his armies?","Up his sleevies."},
                    {"What are the biggest enemies of caterpillars?","Dogerpillers."},
                    {"What do you call an empty can of Cheese Whiz?","Cheese Was."},
                    {"What did Mario say when he broke up with Princess Peach?","\"It's not you, it's a-me, Mario!\""},
                    {"What's the award for being best dentist?"," A little plaque."},
                    {"What did the finger say to the thumb?","I'm in glove with you."},
                    {"What do you call a magician dog? ","A labracadabrador."},
                    {"What concert costs only 45 cents?","50 Cent plus Nickelback."},
                    {"What do sprinters eat before a race?","Nothing, they fast."},
                    {"Who invented the round table?","Sir Cumference."},
                    {"What sound does a nut make when it sneezes?","Cashew!"},
                    {"Why do ghosts love elevators? ","Because it lifts their spirits."},
                    {"What's the best way to carve wood?","Whittle by whittle."},
                    {"Why was the snowman looking through a bag of carrots?","He was picking his nose."},
                    {"What do you call a belt made out of watches?","A waist of time."},
                    {"How can you make seven an even number?","Just take away the \"s\"!"},
                    {"What did the lawyer wear to court?","A lawsuit!"},
                    {"What do you call HIJKLMNO?","Water. (H20!)"},
                    {"How do you find Will Smith in the snow?","Just follow the fresh prints!"},
                    {"Why did the golfer wear two pairs of pants? ","Because he always gets a hole in one!"},
                    {"Did you hear about the kidnapping at school?","It's fine, he eventually woke up!"},
                    {"What kind of dinosaur loves to sleep? ","Well, now, all of them."},
                    {"Why did the teacher love the whiteboard?","She just thought it was remarkable!"},
                    {"A guy told me, \"Nothing rhymes with orange.\"","So I replied, \"No it doesn't.\""},
                    {"What did the drummer call his two twin daughters? ","Anna one, Anna two."},
                    {"What do you call a boomerang that never comes back?"," A stick."},
                    {"What to hear a joke about paper?","Never mind, it's tearable."},
                    {"When is a joke a dad joke?","When it's apparent!"},
                    {"Girl: So, how often do you shave?\n" +"\n" +"Man: Well, about 15-20 times every day.\n" +"\n" +"Girl: My god, are you some kind of crazy?\n" +"\n" +"Man: No, I’m a barber.","0"},
                    {"What’s black, red, black, red, black, red?","A zebra with a sun burn."},
                    {"What is green and sits crying in the corner?","The incredible Sulk."},
                    {"What flutters about and clearly never had a bath in its entire life?","Stinkerbell."},
                    {"Why are pigs not allowed to ride bikes?","Because they lack the thumbs to ring the bell."},
                    {"Where do we get virgin wool from?","Ugly sheep."},
                    {"What is white and sits on your TV?","A fly wearing a nightdress."},
                    {"It has four legs and it can fly, what is it?","Two birds."},
                    {"He: I have good news and bad news. Which do you want to hear first?\n" +"\n" +"She: The good news.\n" +"\n" +"He: The good news is that I have no bad news.","0"},
                    {"What did the cowboy say to the cow that stood on the barn roof?","Get down, cow!"},
                    {"What is transparent and smells like worms?","A bird's fart"},
                    {"What is red and drifts over a desert?","A fart with a sunburn."},
                    {"What is the tallest piece of furniture?","The bookcase. It’s got the most stories."},
                    {"Last words of a highly poisonous snake?","\"Shit, I bit myself on the tongue!\""},
                    {"What is blue and smells like red paint?","Blue paint."},
                    {"One friend to another:\n" + "\n" +"Why are you giving me an apricot?","I heard there’s no way you can get a date."},
                    {"Why do cows wear bells?","Because their horns don’t work."},
                    {"A crying son runs to his mom: “Mom, mom, (sniff), Grandpa slapped me in the face.”","Grandpa approaches: “Stop lying or I’ll do it again!”"},
                    {"A woman starts chatting to a man on a subway: \"Hello my name is Margaret.\"","The man replies: \"Mine not.\""},
                    {"What do you get when you cross a dog and an antenna?","A Golden Receiver."},
                    {"It’s always scary when a computer turns into a zombie.","It has many mega-bites."},
                    {"What would happen if you threw blue sneakers into the Red Sea?","They would get wet."},
                    {"I never knew eggs were good for the eyes, but my cousin claims they gave him eggcelent vision.","0"},
                    {"How can you open a banana?","With a monkey!"},
                    {"What would you call a very funny mountain?","Hill Arious"},
                    {"What do you get when you crossbreed a mail pigeon and a parrot?","A mail pigeon who stops to ask for directions."},
                    {"Why do we consider chickens as friendly animals?","Because they lay their eggs instead of throwing them."},
                    {"What tea can vary in taste from bitter to sweet?","Realitea."},
                    {"Two grains of sand go through the desert.","One to the other: \"I have the feeling somebody is watching me.\""},
                    {"They were laughing when I told them I’m becoming a stand-up comedian. They’re not laughing now that I've become one","0"},
                    {"What would Bears become without Bees?","Just ears."},
                    {"What is yellow and smells of bananas?","Ape vomit."},
                    {"A horse goes into a bar. Barkeeper: \"Why such a long face?\"","0"},
                    {"What is small, grey and triangular?","The shadow of the green triangle!"},
                    {"A gummy bear sits on a power line and says to the other gummy bear, \"brzzzzztbbzbtbtbbzzztbrrrrzt\"","0"},
                    {"Why did the elephant wear green socks?","Because the red ones were wet."},
                    {"What is a cannibal’s natural first choice in a restaurant?","The waiter."},
                    {"A guy orders at a bakery, \"I'd like 19 buns please.\"\n" +"\n" +"The baker suggests, \"I think you should take 20, sir.\"\n" +"\n" +"\"Why?\" asks the man, puzzled.\n" +"\n" +"The baker replies, \"That way, you would have one more!\"","0"},
                    {"What is yellow and black and flies?","A group of mustard seeds in leather jackets!"},
                    {"What is the difference between a soccer star and a bank robber?","Bank robber: “Give me the money or I shoot!”\n" + "\n" +"Soccer star: “Give me the money or I don’t shoot!”"},
                    {"What is black and white and waits on the washing line?","A fly in a wedding dress."},
                    {"Dad to his son: \" I have nothing against us sharing an opinion, as long as it means that I keep my opinion and you share mine.\"","0"},
                    {"What is the difference between an eggplant and a chicken?","They're both purple, except for the chicken."},
                    {"A drummer and a bass player jump off a skyscraper – Boom Boom.","0"},
                    {"How did Moses cut the sea in half?","With a seasaw."},
                    {"Men are from Mars.\n" +"\n" +"Women are from Venus.\n" +"\n" + "Cows are from the Moooooooooon.","0"},
                    {"The last 4 letters in the word \"queue\" are silent. Can they be waiting their turn?","0"},
                    {"Isn’t it funny that when boats get sick, they actually go to the doc(k)?","0"},
                    {"What does a skeleton say when he enters a bar?","BONEjour!"}
            };
}